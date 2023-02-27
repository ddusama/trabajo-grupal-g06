from django.shortcuts import render
from django.http import HttpResponse, JsonResponse
from app.models import Author
from django.views import View
from django.utils.decorators import method_decorator
from django.views.decorators.csrf import csrf_exempt
import json


# Request Handler
# request -> response

@method_decorator(csrf_exempt, name='dispatch')
class AuthorView(View):
    def get(self, request, id=0):
        if id == 0:
            authors = list(Author.objects.values())
            return JsonResponse(authors, safe=False)
        else:
            authors = list(Author.objects.filter(id=id).values())
            return JsonResponse(authors[0], safe=False)

    def post(self, request):
        author_body = json.loads(request.body)
        author = Author(first_name=author_body['first_name'], last_name=author_body['last_name'])
        author.save()
        return HttpResponse(status=201)

    def put(self, request, id):
        author_body = json.loads(request.body)
        author = Author.objects.get(id=id)
        author.first_name = author_body['first_name']
        author.last_name = author_body['last_name']
        author.save()
        return HttpResponse(status=204)

    def delete(self, request, id):
        author = Author.objects.get(id=id)
        author.delete()
        return HttpResponse(status=204)
