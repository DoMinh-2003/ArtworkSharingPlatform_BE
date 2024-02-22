echo "Building app..."
./mvnw clean package

echo "Deploy files to server..."
scp -r target/ArtworkSharingPlatform.jar root@128.199.178.23:/var/www/be

ssh root@128.199.178.23 <<EOF
pid=\$(sudo lsof -t -i :8080)

if [ -z "\$pid" ]; then
    echo "Start server..."
else
    echo "Restart server..."
    sudo kill -9 "\$pid"
fi
cd /var/www/be
java -jar ArtworkSharingPlatform.jar
EOF
exit
echo "Done!