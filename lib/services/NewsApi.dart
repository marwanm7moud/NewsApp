import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:newsapp/models/Article.dart';
import 'package:newsapp/models/ArticlesList.dart';

class NewsApi {
  String apiKey = "1667bd4ccd044d58b8dc21744abc8408";

  Future<List<Article>> fetchDataFromApi() async{
    http.Response res = await http.get(Uri.parse("https://newsapi.org/v2/top-headlines?country=eg&apiKey=$apiKey"));
    if(res.statusCode!=200)
      return null;
    var data = jsonDecode(res.body);
    ArticlesList articlesListFromApi= ArticlesList.fromJson(data);
    List<Article> articleList = articlesListFromApi.Articles.map((e){
      return Article.fromJson(e);
    }).toList();
    return articleList;
  }

  Future<List<Article>> fetchDataFromApiByCategory(String category) async{
    http.Response res = await http.get(Uri.parse("https://newsapi.org/v2/top-headlines?country=eg&category=$category&apiKey=$apiKey"));
    if(res.statusCode!=200)
      return null;
    var data = jsonDecode(res.body);
    ArticlesList articlesListFromApi= ArticlesList.fromJson(data);
    List<Article> articleList = articlesListFromApi.Articles.map((e){
      return Article.fromJson(e);
    }).toList();
    return articleList;
  }
}
