# Generated by Django 4.0.5 on 2022-06-05 15:26

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('authapp', '0002_model_last_updated_time'),
    ]

    operations = [
        migrations.AlterField(
            model_name='model',
            name='last_updated_time',
            field=models.DateTimeField(auto_now=True),
        ),
    ]