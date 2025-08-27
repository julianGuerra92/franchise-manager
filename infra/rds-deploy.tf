provider "aws" {
  region  = "us-east-1"
}

resource "aws_default_vpc" "default_vpc" {
  tags = {
    Name = "default vpc"
  }
}

data "aws_availability_zones" "available_zones" {}

resource "aws_default_subnet" "subnet_az1" {
  availability_zone = data.aws_availability_zones.available_zones.names[0]
}

resource "aws_default_subnet" "subnet_az2" {
  availability_zone = data.aws_availability_zones.available_zones.names[1]
}

resource "aws_security_group" "webserver_security_group" {
  name        = "webserver security group"
  description = "enable http access on port 80"
  vpc_id      = aws_default_vpc.default_vpc.id

  ingress {
    description      = "http access"
    from_port        = 80
    to_port          = 80
    protocol         = "tcp"
    cidr_blocks      = ["0.0.0.0/0"]
  }

  egress {
    from_port        = 0
    to_port          = 0
    protocol         = "-1"
    cidr_blocks      = ["0.0.0.0/0"]
  }

  tags   = {
    Name = "webserver security group"
  }
}

resource "aws_security_group" "database_security_group" {
  name        = "database security group"
  description = "enable mysql/aurora access on port 3306"
  vpc_id      = aws_default_vpc.default_vpc.id

  ingress {
    description      = "mysql web server access"
    from_port        = 3306
    to_port          = 3306
    protocol         = "tcp"
    security_groups  = [aws_security_group.webserver_security_group.id]
  }

  ingress {
    description      = "mysql local access"
    from_port        = 3306
    to_port          = 3306
    protocol         = "tcp"
    cidr_blocks  = ["181.32.146.30/32"]
  }

  egress {
    from_port        = 0
    to_port          = 0
    protocol         = "-1"
    cidr_blocks      = ["0.0.0.0/0"]
  }

  tags   = {
    Name = "database security group"
  }
}

resource "aws_db_subnet_group" "database_subnet_group" {
  name         = "database-subnets"
  subnet_ids   = [
    aws_default_subnet.subnet_az1.id,
    aws_default_subnet.subnet_az2.id
  ]
  description  = "subnet group for the rds instance"

  tags   = {
    Name = "database-subnets"
  }
}


variable "db_username" {}
variable "db_password" {
  sensitive = true
}

resource "aws_db_instance" "db_instance" {
  engine                  = "mysql"
  engine_version          = "8.0"
  multi_az                = false
  identifier              = "accenture-rds-instance-dev"
  username                = var.db_username
  password                = var.db_password
  instance_class          = "db.t3.micro"
  allocated_storage       = 20
  db_subnet_group_name    = aws_db_subnet_group.database_subnet_group.name
  vpc_security_group_ids  = [aws_security_group.database_security_group.id]
  db_name                 = "accenture_db"
  skip_final_snapshot     = true
  publicly_accessible     = true
}