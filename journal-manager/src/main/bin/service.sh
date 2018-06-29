SOURCE="$0"
while [ -h "$SOURCE"  ]; do # resolve $SOURCE until the file is no longer a symlink
    DIR="$( cd -P "$( dirname "$SOURCE"  )" && pwd  )"
    SOURCE="$(readlink "$SOURCE")"
    [[ $SOURCE != /*  ]] && SOURCE="$DIR/$SOURCE" # if $SOURCE was a relative symlink, we need to resolve it relative to the path where the symlink file was located
done
cd "$( cd -P "$( dirname "$SOURCE"  )" && pwd  )"/..

APP_HOME=`pwd`

MAIN_CLASS=com.sixliu.credit.product.StartUp


PID_FILE=$APP_HOME'/bin/program.pid'

CLASSPATH=${APP_HOME}'/conf'


##########
# Setup JAVA if unset
##################################################
if [ -z "$JAVA" ]; then
  JAVA=$(which java)
fi

if [ -z "$JAVA" ]; then
  echo "Cannot find a Java JDK. Please set either set JAVA or put java (>=1.5) in your PATH." 2>&2
  exit 1
fi


#-------------------------- class path jar package -----------------------
SEARCH_JAR_PATH=(
        "$APP_HOME/lib"
        )

for jarpath in ${SEARCH_JAR_PATH[@]}; do
        for file in $jarpath/*.jar; do
                # check file is in classpath
                result=$(echo "$CLASSPATH" | grep "$file")
                if [[ "$result" == "" ]]; then
                        CLASSPATH=$CLASSPATH:$file;
                fi
        done
done
#-Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=8000,suspend=n
function start_program(){
	if [ -f $PID_FILE ]; then
      echo "program is running exit."
	  exit 0
    fi
    echo -n "starting program ... "
	  #nohup  $JAVA -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=8000,suspend=n  -classpath $CLASSPATH $MAIN_CLASS --spider.home=${APP_HOME} >/dev/null 2>&1 &
	  nohup  $JAVA -Djava.security.egd=file:/dev/./urandom -classpath $CLASSPATH $MAIN_CLASS >/dev/null 2>&1 &
    if [ $? -eq 0 ]
    then
      if /bin/echo -n $! > "$PID_FILE"
      then
        #sleep 1
        echo STARTED
      else
        echo FAILED TO WRITE PID
        exit 1
      fi
    else
      echo PROGRAM DID NOT START
      exit 1
    fi
}

#-------------------------------------------------------
function stop_program(){
	#--------------------------- kill program start --------------------	
	echo -n "Stopping program ... "
    if [ ! -f "$PID_FILE" ]
    then
      echo "no the program to stop (could not find file $PID_FILE)"
    else
     	kill  $(cat "$PID_FILE")
		pids=`pgrep chrome`
		kill -9 $pids
      rm "$PID_FILE"
      echo STOPPED
    fi
}




ACTION=$1
case "$ACTION" in
  start)
	start_program
  ;;
  stop)
	stop_program
  ;; 
  restart)
	stop_program
	start_program
  ;; 
  check)
    echo "Checking arguments to $PROJECT_NAME: "
    echo "JAVA_HOME     		=  $JAVA_HOME"
    echo "PROJECT_HOME     	=  $PROJECT_HOME"
    echo "LOG_FILE     		=  $LOG_FILE"
    echo "MAIN_JAR     		=  $MAIN_JAR"
    echo "MAIN_CLASS		=  $MAIN_CLASS"
    echo "JAVA_OPTIONS   		=  ${JAVA_OPTIONS[*]}"
    echo "SEARCH_JAR_PATH	=  ${SEARCH_JAR_PATH[*]}"
    echo "JAVA           		=  $JAVA"
    echo "CLASSES_PATH      	=  $CLASSES_PATH"
    echo

    if [ -f $PID_FILE ];
    then
      echo "RUNNING PID	=$(cat "$PID_FILE")"
      exit 0
    fi
    exit 1

    ;;
  *)
    usage
    ;;
esac
exit 0