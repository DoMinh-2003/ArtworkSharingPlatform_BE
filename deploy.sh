echo "Building app..."
./mvnw clean package

echo "Deploy files to server..."
scp -r target/ArtworkSharingPlatform.jar root@68.183.180.21:/var/www/becode

ssh root@68.183.180.21 <<EOF
pid=\$(sudo lsof -t -i :8080)

if [ -z "\$pid" ]; then
    echo "Start server..."
else
    echo "Restart server..."
    sudo kill -9 "\$pid"
fi
cd /var/www/becode
java -jar ArtworkSharingPlatform.jar
EOF
exit
echo "Done!"