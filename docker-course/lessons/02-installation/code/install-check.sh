#!/bin/bash
# Check Docker installation and version
echo "Checking Docker installation..."
docker --version || { echo 'Docker is not installed.'; exit 1; }
echo "Docker is installed."
echo "Running hello-world container..."
docker run --rm hello-world