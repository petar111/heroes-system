#!/bin/bash

set -e

psql -U postgres -d postgres -c "SELECT 1 FROM pg_database WHERE datname = 'your_database'" | \
grep -q 1 || psql -U postgres -c "CREATE DATABASE your_database
