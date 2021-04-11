import 'package:flutter/material.dart';
import 'package:newsapp/models/Article.dart';
import 'package:newsapp/services/NewsApi.dart';

class SearchScreenProvider with ChangeNotifier{

  List<Article> articlesBySearch;
  Future<void> fetchArticlesSearch(String value)async{
    articlesBySearch = await NewsApi().fetchDataFromApiToSearch(value);
    notifyListeners();
  }
  nullArticle(){
    articlesBySearch=null;
    notifyListeners();
  }

}