#!/usr/bin/env bash
set -e

./shared/scripts/wait-up.sh "https://$1:8765/processos/manage/info" 300
