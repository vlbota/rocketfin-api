# Configuração de ``application.properties`` e ``Docker compose``

## Configuração de banco de dados
* tipo de banco: ``Postgresql``
* nome banco: ``rocketfin_db`` 
* username: {USERNAME_DB_ROCKETFIN}
* password: {PASSWORD_DB_ROCKETFIN}
* address: {ADDRESS_DB_ROCKETFIN}

O banco deve ter o atributo de validade em prod e update em dev
O projeto deve ter um arquivo .env com as variáveis de ambiente acima

## Variaveis de ambiente do projeto

- BOT_TELEGRAM