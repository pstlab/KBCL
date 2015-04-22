#!/bin/bash
#
# PRINT DOMAIN INFORMATION
echo
echo "DDL Domain Information of \"$1\""
echo
#
# PRINT NUMBER OF STATE VARIABLE
echo "Number of State Variables"
awk 'BEGIN {count = 0} /COMPONENT/ {count+=1} END {print count}' < $1
#
#
# PRINT NUMBER OF SYNCHRONIZATIONS
echo "Number of Synchronizations"
awk 'BEGIN {count = 0} /VALUE.*\(\).*\{/ {count += 1} END {print count}' < $1
#
#
# PRINT NUMBER OF VALUES
echo "Number of values"
awk 'BEGIN {count = 0} /VALUE.*\(\).*\[/ {count += 1} END {print count}' < $1
echo
