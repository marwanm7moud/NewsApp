import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:newsapp/models/Article.dart';
import 'package:newsapp/viewmodels/HomeScreenProvider.dart';
import 'package:newsapp/viewmodels/SearchScreenProvider.dart';
import 'package:newsapp/widgets/ArticleWidget.dart';
import 'package:provider/provider.dart';
class SearchScreen extends StatefulWidget {
  @override
  _SearchScreenState createState() => _SearchScreenState();
}

class _SearchScreenState extends State<SearchScreen> {
  String search;
  @override
  Widget build(BuildContext context) {
    List<Article> articles = Provider.of<SearchScreenProvider>(context).articlesBySearch;
    return Scaffold(
      appBar: AppBar(
        leading: IconButton(
          icon: Icon(Icons.arrow_back ,color:Theme.of(context).textTheme.headline6.color ,),
          onPressed: (){
            Navigator.pop(context);
            Provider.of<SearchScreenProvider>(context,listen: false).nullArticle();
          },
        ),
      ),
      body: Container(
        padding: EdgeInsets.only(top: 15),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          mainAxisAlignment: MainAxisAlignment.start,
          children: [
            Container(
              decoration: BoxDecoration(
                borderRadius: BorderRadius.circular(15),
                color: Colors.grey[600],
              ),

              child: TextFormField(
                onChanged: (val){
                  setState(() {
                    search = val;
                    Provider.of<SearchScreenProvider>(context,listen: false).nullArticle();

                    Provider.of<SearchScreenProvider>(context,listen: false).fetchArticlesSearch(search);
                  });
                },
                cursorColor: Colors.red,
                style: TextStyle(color:Theme.of(context).textTheme.headline6.color ),
                decoration:InputDecoration(
                  enabledBorder: Theme.of(context).inputDecorationTheme.enabledBorder,
                  border:Theme.of(context).inputDecorationTheme.border,

                ),
              ),
            ),
            SizedBox(height: 15,),
            articles==null
                ?
            Center(child: CircularProgressIndicator(),)
                :
            Expanded(
              child: ListView.builder(
                  itemCount: articles.length,
                  itemBuilder: (BuildContext context, int index){
                    return ArticleWidget(
                        imageUrl: articles[index].urlToImage,
                        title: articles[index].title,
                        publishDate: articles[index].publishedAt);
                  }
              ),
            )
          ],
        ),
      ),
    );
  }
}
