from django.urls import path
from app.views import AuthorView

urlpatterns = [
    path('', AuthorView.as_view()),
    path('/<int:id>', AuthorView.as_view())
]
