git clone https://github.com/ddusama/trabajo-grupal-g06.git && cd ./trabajo-grupal-g06 && cd ./app-books && gradlew libertyPackage && docker build -t ddusama/app-books:1.0.0 . && cd .. && cd ./app-authors && docker build -t ddusama/app-authors:1.0.0 . && cd .. && docker push ddusama/app-books:1.0.0 && docker push ddusama/app-authors:1.0.0 && docker compose up

