


## Table of contents
* [Task](#task)
* [OtherSpecs](#OtherSpecs)
* [Technologies](#technologies)
* [Setup](#setup)
* [Todo](#Todo)


## Task

* Read the objects from the attached file, consider the file contents is updated 1/min.

* Create Queues for each bid type.

* Instantiate a thread per object and based on message type queue it in the appropriate queue.

* Log the bid id(id), timestamp(ts), queue “name”( ty), and payload (decoded from Base64 pl) when the queueing is completed.

## OtherSpecs
* Event-based reading from the stream  
* Spawn a thread for each queue message  
* Use logging library to log output to console

## Technologies
* mvn dependency:list
* Docker
* Bash
* Rabbit MQ
## Setup
#####dev
```
$ cd $PROJECT_DIR
$ ./install_dev.sh
```
#####prod
```
$ cd $PROJECT_DIR
$ ./install_prod.sh
```
## Todo
* Unit/Integration Testing
* Load Balancing
* Continuous Integration and Delivery
