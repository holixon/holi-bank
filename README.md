# holi-bank

Fictional banking application based on https://github.com/AxonFramework/AxonBank - learing CQRS/ES with axon


## Run

using httpie:

in Console(1), type 

`http POST :8080/api/accounts id=1`

to create account with id "1" and initial balance "0"


in Console(2), type:

`http -S  :8080/api/accounts/1`

to subscribe to changes on balance of account "1"

then in Console(1) type

`http PUT :8080/api/accounts/1 id=1 amount=10`

and check expected output in Console(2).
