git clone https://github.com/ddusama/trabajo-grupal-g06.git && cd ./trabajo-grupal-g06 && cd ./app-books && gradlew libertyPackage && docker build -t jaimesalvador/app-books:cuascota-1.0.0 . && cd .. && cd ./app-authors && docker build -t jaimesalvador/app-authors:cuascota-1.0.0 . && cd .. && docker push jaimesalvador/app-books:cuascota-1.0.0 && docker push jaimesalvador/app-authors:cuascota-1.0.0 && docker compose up

