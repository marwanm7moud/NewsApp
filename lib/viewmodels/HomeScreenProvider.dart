import 'package:flutter/material.dart';
import 'package:newsapp/models/Article.dart';
import 'package:newsapp/services/NewsApi.dart';
import 'package:newsapp/view/HomeScreen/ButtonNavigationScreens/BusinessScreen.dart';
import 'package:newsapp/view/HomeScreen/ButtonNavigationScreens/ScienceScreen.dart';
import 'package:newsapp/view/HomeScreen/ButtonNavigationScreens/HealthScreen.dart';
import 'package:newsapp/view/HomeScreen/ButtonNavigationScreens/SportsScreen.dart';
import 'package:provider/provider.dart';

class HomeScreenProvider with ChangeNotifier
{
  int selectedIndex = 0;
  void onItemTapped(int index) {
      selectedIndex = index;
      notifyListeners();
  }
  List<Widget> widgetOptions = <Widget>[
    BusinessScreen(),
    SportsScreen(),
    ScienceScreen(),
    HealthScreen()
  ];





  List<Article> articlesBusiness;
  List<Article> articlesSports;
  List<Article> articlesScience;
  List<Article> articlesHealth;

  Future<void> fetchArticlesBusiness()async{
    articlesBusiness = await NewsApi().fetchDataFromApiByCategory("business");
    notifyListeners();
  }
  Future<void> fetchArticlesSports()async{
    articlesSports = await NewsApi().fetchDataFromApiByCategory("sports");
    notifyListeners();
  }
  Future<void> fetchArticlesScience()async{
    articlesScience = await NewsApi().fetchDataFromApiByCategory("science");
    notifyListeners();
  }
  Future<void> fetchArticlesHealth()async{
    articlesHealth = await NewsApi().fetchDataFromApiByCategory("health");
    notifyListeners();
  }





}