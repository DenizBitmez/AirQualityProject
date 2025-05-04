#!/bin/bash

DURATION=60
RATE=5
ANOMALY_CHANCE=20

while [[ "$#" -gt 0 ]]; do
    case $1 in
        --duration=*) DURATION="${1#*=}" ;;
        --rate=*) RATE="${1#*=}" ;;
        --anomaly-chance=*) ANOMALY_CHANCE="${1#*=}" ;;
    esac
    shift
done

API_URL="http://localhost:8080/api/quality/save"
LATITUDE=40.7128
LONGITUDE=-74.0060
END_TIME=$((SECONDS+DURATION))

PARAMETERS=("pm25" "pm10" "no2" "so2" "o3")

generate_value() {
    local param=$1
    local is_anomaly=$2
    if [ "$is_anomaly" -eq 1 ]; then
        echo $((RANDOM % 150 + 150))  # anomali: 150-300
    else
        echo $((RANDOM % 120 + 1))    # normal: 1-120
    fi
}

while [ $SECONDS -lt $END_TIME ]; do
    IS_ANOMALY=$((RANDOM % 100 < ANOMALY_CHANCE ? 1 : 0))

    JSON_PAYLOAD="{\"location\": \"AutoTest\", \"latitude\": $LATITUDE, \"longitude\": $LONGITUDE"

    for param in "${PARAMETERS[@]}"; do
        VALUE=$(generate_value "$param" $IS_ANOMALY)
        JSON_PAYLOAD+=", \"$param\": $VALUE"
    done

    JSON_PAYLOAD+="}"

    RESPONSE=$(curl -s -X POST "$API_URL" -H "Content-Type: application/json" -d "$JSON_PAYLOAD")

    TYPE=$( [ "$IS_ANOMALY" -eq 1 ] && echo "Anomali" || echo "Rastgele" )
    echo "$TYPE veri gönderildi: $JSON_PAYLOAD"
    echo "Yanıt: $RESPONSE"

    sleep $((60 / RATE))
done

echo "Test tamamlandı."
