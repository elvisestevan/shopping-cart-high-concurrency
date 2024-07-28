# shopping-cart-high-concurrency
API that handle shopping carts with high concurrency of customers trying to buy the same product

### TODO List
- [X] Spring Boot Web
- [X] Docker postgres
- [X] Data test
- [X] Endpoint #1 Naive Implementation (implementation, tests, report)
- [ ] Endpoint metrics exporting default metrics of JVM and HTTP
- [ ] Docker prometheus
- [ ] Docker grafana
- [ ] Dashboard on grafana with JVM metrics
- [ ] Dashboard on grafana with HTTP metrics
- [ ] Endpoint #2 Read Committed Locking (implementation, tests, report)
- [ ] Endpoint #3 Serializable Locking (implementation, tests, report)
- [ ] Endpoint #4 FOR UPDATE locking (implementation, tests, report)
- [ ] Endpoint #5 Optimistic Locking (implementation, tests, report)
- [ ] Endpoint #6 Kafka sharding by product_id (implementation, tests, report)
- [ ] Endpoint #7 Create another table to store all items in stock (implementation, tests, report)

### Functional Requirements
- Create a cart
- Get product by id
- Add product to a cart
    - Allow adding product only if product is in stock
    - Reserve product on stock
- Expire cart after 10 minutes and undo reservations

### Non Functional Requirements
- Consistency on stock (handle concurrency when more than one client tries to reserve the same product at the same time)
- Add product to a cart needs to respond within 100ms

### Number of clients
- 3 million of clients adding product to a cart at the same time
- Max of 1000 clients adding the SAME product at the same time to your carts

###### Endpoint #1 Naive Implementation
This endpoint just add a product to a cart without any concern of concurrency

###### Endpoint #2 Read Committed Locking
This endpoint handle concurrency with read committed locking

###### Endpoint #3 Serializable Locking
This endpoint handle concurrency with serializable locking

###### Endpoint #4 FOR UPDATE locking
This endpoint handle concurrency with FOR UPDATE (PostgreSQL) locking

###### Endpoint #5 Optimistic Locking
This endpoint handle concurrency with optmistic locking (versioning product)

###### Endpoint #6 Kafka sharding by product_id
This endpoint handle concurrency with kafka sharding by product_id

###### Endpoint #7 Create another table to store all items in stock
This endpoint will write in a previous created table that will be using to store items individually, for example, if the product X has quantity of 10 available, in that new table will have 10 rows as "not reserved"