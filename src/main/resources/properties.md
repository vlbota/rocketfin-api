# Configuração de ``application.properties`` e ``Docker compose``

O projeto deve ter um arquivo .env com as variáveis de ambiente marcadas com {} e dados  inicialmente mockados

## Configuração de banco de dados
* tipo de banco: ``Postgresql``
* nome banco: ``rocketfin_db`` 
* username: {USERNAME_DB_ROCKETFIN}
* password: {PASSWORD_DB_ROCKETFIN}
* address: {ADDRESS_DB_ROCKETFIN}

Crie um profile dev em arquivo propertie separado
O banco deve ter o atributo de geração de validade em prod e update em dev


## E-mail

Use o quarkus mailer, configure com dados mockados

## Variaveis de ambiente do projeto

Classe src.main.java.vlb.developer.utils.environments.Comunications

- BOT_TELEGRAM - vai puxar do properties env.telegram.bot={TELEGRAM_BOT}
- BOT_CHAT_MESSAGE_FAILS - vai puxar do properties env.telegram.chat.message-fails={TELEGRAM_CHAT_MSG_FAILS}

Classe src.main.java.vlb.developer.utils.environments.Paths

- FRONT_URL - vai puxar do properties env.path.front-end={FRONT_PATH}
- BACK_URL - vai puxar do properties env.path.back-end={BACK_PATH}


configure nesse projeto o RabbitMQ