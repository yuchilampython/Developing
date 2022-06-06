from django.shortcuts import render
from django.http import HttpResponse
from django.template import loader

# Create your views here.

def index(request):
    return HttpResponse('This is an authentication app.')

def login_page(request):
    template = loader.get_template('login.html')
    return HttpResponse(template.render())

def signup_page(request):
    template = loader.get_template('signup.html')
    return HttpResponse(template.render())
