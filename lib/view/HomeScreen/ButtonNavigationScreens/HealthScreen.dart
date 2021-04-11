import 'package:flutter/material.dart';
import 'package:newsapp/models/Article.dart';
import 'package:newsapp/viewmodels/HomeScreenProvider.dart';
import 'package:newsapp/widgets/ArticleWidget.dart';
import 'package:provider/provider.dart';
class HealthScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    List<Article> articles = Provider.of<HomeScreenProvider>(context).articlesHealth;
    return Scaffold(
        body: articles==null?Center(child: CircularProgressIndicator(),):ListView.builder(
            itemCount: articles.length,
            itemBuilder: (BuildContext context, int index){
              return ArticleWidget(
                  imageUrl: articles[index].urlToImage,
                  title: articles[index].title,
                  publishDate: articles[index].publishedAt);
            }
        )
    );
  }
}
