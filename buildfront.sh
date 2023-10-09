#!/usr/bin/env sh


cd alt13front
git pull origin main

#npm install
#npm install react-scripts

npm run build

cp -r build/* ../src/main/resources/static/

cd ..

