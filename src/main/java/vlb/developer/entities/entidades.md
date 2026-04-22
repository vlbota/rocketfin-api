# Entidades de contas

Nome de entidades no banco não contém o ``Enty`` apenas o nome inicial
e com tudo minusculo

Em camel case como descrito como ``CategoryBillEnty`` no nome do banco seria
category_bill, assim como os campos também como ``createdAt`` seria created_at

## Entidade AccountEnty
* id - primary key
* description - string - not null
* codeBank - string
* client - manytoone com classe ClientEny

## Entidade AccountRegistryBalanceEnty
* id - primary key
* account - manytoone com AccountEnty
* createdAt
* dateBalance
    
## Entidade AccountTransactionRegistryEnty
* id - primary key - long
* account - manytoone com AccountEnty
* type - TypeOperationEnum(ENTRADA, SAIDA)

## Entidade BillEnty
* id - primary key - long
* identifier - string
* description - not null- string
* value - not null - bigdecimal
* due - not null - localdate
* createdAt - not null - offsetdatetime
* client - manytoone para ClientEnty - not null
* category - manytoone para CategoryBillEnty
* type - TypeOperationEnum(ENTRADA, SAIDA) - not null - fica em vlb/developer/bills/enumerates
* paid - boolean - not null

## Entidade PayMethodEnty
* id - primary key - long
* description - string
* client - manytoone para ClientEnty
* account - manytoone com AccountEnty

## Entidade BillPaidEnty
* id - primary key
* paymentMethod
* valuePaid
* paidAt
* createdAt
* bill - manytoone para BillEnty

## Entidade CategoryBillEnty
* id - primary key
* identifier - string
* description - not null
* client - manytoone para ClientEnty
* category - Um manytoone para essa mesma classe

## Entidade ClientEnty
* id - primary key - UUID V7
* name - not null
* nickname - not null
* username - not null
* password - not null
* email - not null
* countryCode - string
* phoneNumber - string
* createdAt - not null