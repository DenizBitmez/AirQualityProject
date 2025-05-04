#!/bin/bash

LATITUDE=$1
LONGITUDE=$2
PARAMETER=$3
VALUE=$4

API_URL="http://localhost:8080/api/quality/save"

JSON_PAYLOAD=$(cat <<EOF
{
    "location": "Manual Location",
    "$PARAMETER": "$VALUE",
    "latitude": $LATITUDE,
    "longitude": $LONGITUDE
}
EOF
)

curl -X POST "$API_URL" \
     -H "Content-Type: application/json" \
     -d "$JSON_PAYLOAD"

echo "Veri başarıyla gönderildi."
