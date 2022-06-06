from django.db import models

# Create your models here.
class Model(models.Model):
    firstname = models.CharField(max_length=255, blank=False)
    lastname = models.CharField(max_length=255, blank=False)
    email = models.EmailField(max_length=254, blank=False, unique=True)
    salt = models.BinaryField(max_length=256, blank=False)
    password_hash = models.BinaryField(max_length=64, blank=False) #SHA-256: 32-bytes
    last_updated_time = models.DateTimeField(auto_now=True, blank=False)