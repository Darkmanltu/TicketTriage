# TicketTriage
## Small scale project to integrate huggingface models to RESTful api

### Project inteded for showcase and is not production ready

***
# How to get started
### prerequisites: https://huggingface.co account and access token to read and access inference models
### selected model from: https://huggingface.co/inference/models
WARNING: Uptime is not guaranteed, and depends on huggingface model inference provider.
This project was using the following models to test and run the project:
-Qwen2.5-7B-Instruct
-gemma-2-2b-it
### Final deployment was with Qwen2.5-7B-Instruct model for its cheap token cost and uptime.

# To run the project you must add the following in the file src/main/resources/application.properties
### huggingface.api.token=YOUR_HF_API_TOKEN
### huggingface.api.model=MODEL_NAME
where YOUR_HF_API_TOKEN is the token generated from hugging face acess tokens and MODEL_NAME is the selected model from hugging face inference providers.

more documentation on how to call models is on: https://huggingface.co/inference/models
https://huggingface.co/inference/get-started
