echo "Building app..."
./mvnw clean package

echo "Deploy files to server..."
scp -r -i ~/Desktop/becode.vn target/beco.jar root@143.198.214.247:/var/www/becode_be/

echo "Done!"