# Description 
Telegram bot for save minimal info about coffee drinking.

# Local run
- run postgresql in docker
- set bot token in bot_token.txt
- run application

# Production run
- build jar by `mvn build package` (docker must be run for tests pass)
- set bot token in bot_token.txt
- run docker-compose.yaml