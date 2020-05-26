# Docker Started

##为什么使用docker
在微服务的大背景下，一台物理机或者云主机可能要运行很多应用。应用必须依赖于开发环境。当我们遇到拓展物理机、云主机、应用迁移等场景，必然要重新搭建开发环境。这时，虚拟化技术就很好地保证环境一致、配置一致，并且让你更高效地迁移应用。
Docker正是应对这种场景的虚拟化技术。例如java，只要机器上安装了JVM，一份代码到处运行。应用好比java，只要机器上安装docker，我们事先保存的镜像可以到处运行。这些镜像可以是nginx、php、mysql、数据仓库等，无论你的主机从ubuntu迁移到centos，还是windows迁移linux，只要主机安装了docker，就能迅速地部署好新环境，并且保持环境、配置一致。


## 使用yum安装docker(linux系统 centos版)

###安装docker 
yum -y install docker

###启动docker后台服务
service docker start

###测试运行docker镜像
docker run hello-word(由于本地没有docker镜像 所以会去远程仓库down一个镜像下来)

###查看docker版本
docker version

###Docker上安装nginx(事例)
docker pull nginx

###查看当前下载的redis镜像
docker images

###删除镜像
docker rmi -f {repository}:{tag}
或
docker rmi -f {image id}

###启动容器

docker run --name my_nginx -p 80:80 -d nginx

--name 指定容器名，"--name my_nginx"指定容器名为"my_nginx";

-p     映射端口，"-p 80:80"表示映射容器80端口到主机80端口，
       参数格式ip:hostPort:containerPort | ip::containerPort | hostPort:containerPort | containerPort
       
-d     在后台运行，并打印container id
 
###查看容器状态
docker ps

###进入容器
docker exec -it my_nginx bash
进入容器之后,就像普通的linux那样操作

###停止容器
docker stop my_nginx (也可以写id) 

###查看停止状态容器
docker ps -a

###重启容器
docker start my_nginx

###查看容器状态
docker ps

###删除容器(删除容器前,必须先停止容器)
docker rm my_nginx
再次查看容器的转态
docker ps -a
