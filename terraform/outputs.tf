output "public_ip" {
  value = aws_instance.public_ip
}

output "bucket_name" {
  value = aws_s3_bucket.example.bucket
}