class ArticlesList {


  final List<dynamic> Articles;
  ArticlesList({this.Articles});

  factory ArticlesList.fromJson(Map jsondata)
  {
    return ArticlesList(
      Articles:jsondata["articles"]
    );
  }




}