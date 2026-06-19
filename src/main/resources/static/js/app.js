const form = document.getElementById('comment-form');
const statusEl = document.getElementById('status');
const submitBtn = document.getElementById('submit-btn');
const ticketsEl = document.getElementById('tickets');
const refreshBtn = document.getElementById('refresh-btn');

form.addEventListener('submit', async (e) => {
    e.preventDefault();

    submitBtn.disabled = true;
    statusEl.textContent = 'Submitting...';
    statusEl.className = 'status';

    const payload = {
        comment: document.getElementById('comment').value,
        channel: document.getElementById('channel').value
    };

    try {
        const res = await fetch('/api/comment', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(payload)
        });

        if (!res.ok) {
            throw new Error(`Server returned ${res.status}`);
        }

        statusEl.textContent = 'Submitted.';
        statusEl.className = 'status ok';

        form.reset();
        await loadTickets();

    } catch (err) {
        statusEl.textContent = `Failed to submit: ${err.message}`;
        statusEl.className = 'status error';
    } finally {
        submitBtn.disabled = false;
    }
});

refreshBtn.addEventListener('click', loadTickets);

async function loadTickets() {
    ticketsEl.innerHTML = '<p class="empty">Loading...</p>';

    try {
        const res = await fetch('/api/tickets');
        const tickets = await res.json();

        if (!tickets.length) {
            ticketsEl.innerHTML = '<p class="empty">No tickets yet.</p>';
            return;
        }

        ticketsEl.innerHTML = tickets.map(ticket => `
            <div class="ticket">
                <div class="ticket-header">
                    <span class="ticket-title">${escapeHtml(ticket.title || '(untitled)')}</span>
                    <span class="badge priority-${(ticket.priority || 'low').toLowerCase()}">
                        ${ticket.priority || 'n/a'}
                    </span>
                </div>
                <div class="category">
                    ${escapeHtml(ticket.category || 'uncategorized')}
                </div>
                <div class="summary">
                    ${escapeHtml(ticket.summary || '')}
                </div>
            </div>
        `).join('');

    } catch (err) {
        ticketsEl.innerHTML =
            '<p class="status error">Failed to load tickets.</p>';
    }
}

function escapeHtml(str) {
    const div = document.createElement('div');
    div.textContent = str;
    return div.innerHTML;
}

loadTickets();