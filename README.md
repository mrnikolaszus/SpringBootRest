This is a simple REST API designed for managing equipment and storage. (Test Pet Project)
It provides endpoints to perform CRUD (Create, Read, Update, Delete) operations on equipment and storage entities.
The APfollows RESTful principles for easy integration into various applications.

GET /api/eq: Retrieve a list of all equipment.
GET /api/eq/:id: Retrieve details of a specific piece of equipment.
POST /api/eq: Add new equipment to the database.
PUT /equipment/:id: Update information for a specific piece of equipment.
DELETE /equipment/:id: Delete a piece of equipment.

GET /api/storage: Retrieve a list of all storage units.
GET /api/storage/:id: Retrieve details of a specific storage unit.
POST /api/storage: Add a new storage unit to the database.
PUT /api/storage:id: Update information for a specific storage unit.
DELETE /api/storage:id: Delete a storage unit.
