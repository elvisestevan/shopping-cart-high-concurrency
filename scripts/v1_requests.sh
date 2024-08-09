#!/bin/bash

URL='http://localhost:7000/api/v1/products/01J2M55YGRHWV1T72MK3PQXBYS/reservations'

if [ -z "$1" ]; then
  echo "Usage: $0 <number_of_requests>"
  exit 1
fi

NUM_REQUESTS=$1

# Function to make a POST request and print the status code
make_request() {
  local request_number=$1
  local status_code

  status_code=$(curl --location --request POST "$URL" --write-out "%{http_code}" --header 'Content-Type: application/json' --data '{"quantity": 1}' --silent --output /dev/null)

  #echo "Request $request_number: Status code: $status_code"
}

# Loop to call the function asynchronously NUM_REQUESTS times
i=1
while [ $i -le $NUM_REQUESTS ]; do
  make_request "$i" &
  i=$((i + 1))
done

# Wait for all background jobs to finish
wait
echo "$NUM_REQUESTS requests sent"
