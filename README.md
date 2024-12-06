Backend Tech Challenge

Usage of Command Prompt or URL
1. Listing Service
    - Get All Listing (URL) = http://localhost:8081/listings
    - Get Specific User Listing (CMD) = curl -X GET "http://localhost:8081/listings?pageNum=1&pageSize=10&userId=1"
    - Create Listing (CMD) = curl localhost:8081/listings -XPOST \    -d userId=1 \    -d listingType=rent \    -d price=4500
2. User Service
   - Get All Users (URL) = http://localhost:8081/users
   - Get Specific User (URL) = http://localhost:8081/users/{id}
   - Create User (CMD) = curl -X POST http://localhost:8081/users -H "Content-Type: application/json" -d "{\"username\": \"nama_user\"}"
3. Public Api
   - Get All Listings (URL) = http://localhost:8081/public-api/listings
   - Get Specific User Listing (CMD) = curl -X GET "http://localhost:8081/public-api/listings?pageNum=1&pageSize=10&userId=1"
   - Create Listing (CMD) = curl localhost:8081/public-api/listings -XPOST \    -d userId=1 \    -d listingType=rent \    -d price=4500
   - Create User (CMD) = curl -X POST http://localhost:8081/public-api/users -H "Content-Type: application/json" -d "{\"username\": \"nama_user\"}"

Maleo Farrel - 2602076784
06 - 12 - 2024
