provider "aws" {
  region = var.region
}

resource "aws_instance" "machine_devops" {
  ami           = var.ami
  instance_type = var.instance_type

  tags = {
    Name = var.tags.Name
  }
}