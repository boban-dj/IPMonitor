#!/bin/sh
cd $(dirname $(readlink -f $0))

IPM_CMD="java -jar IPMonitor.jar"

which gksudo > /dev/null
if [ $? -eq 0 ]; then
	gksudo "$IPM_CMD" &
	exit
fi

# kdesudo is untested
which kdesudo > /dev/null
if [ $? -eq 0 ]; then
	kdesudo "$IPM_CMD" &
	exit
fi

# su-to-root is untested
which su-to-root > /dev/null
if [ $? -eq 0 ]; then
	su-to-root -X -c "$IPM_CMD" &
	exit
fi
