#!/usr/bin/bash
kill -9 `ps -ef|grep -i myvpn|grep -v grep|awk '{ print $2}'`
pppd call myvpn
