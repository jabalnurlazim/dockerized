set -e
docker login
# https://stackoverflow.com/questions/5947742/how-to-change-the-output-color-of-echo-in-linux
ORANGE='\033[0;33m'
GREEN='\033[0;32m'
NC='\033[0m' # No Color

git submodule init
git submodule update --recursive --remote --merge --force
docker-compose pull
docker-compose up -d

# Service Access Information Section
echo "Kindly test in your local:"
echo "java"
echo "-url: http://localhost:8080/swagger-ui.html"
echo ""
echo "nuxtjs"
echo "-url: http://localhost:3000"
echo ""

printf "PLEASE WAIT PATIENTLY FOR BACK-END, it may currently be building. Kindly check with ${ORANGE}'docker-compose logs --tail=100 -f java'${NC}"
echo ""
printf "PLEASE WAIT PATIENTLY FOR FRONT-END, it may currently be building. Kindly check with ${GREEN}'docker-compose logs --tail=100 -f nuxtjs'${NC}"
echo ""