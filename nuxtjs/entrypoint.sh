#!/bin/bash

# npm install  # uncomment to automate the whole nuxtjs lifecycle via restart
if [ ! -d "node_modules" ]; then
  npm install
fi
npm run dev