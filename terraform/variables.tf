
variable "region" {
  default = "us-west-2"
}

variable "instance_type" {
  default = "t2.micro"
}

variable "ami" {
  default = "2023.0.20230329.0"
}

variable "tags" {
  default = {
    Name = "machine_devops-instance"
  }
}