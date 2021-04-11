import 'package:flutter/material.dart';

class ArticleWidget extends StatefulWidget {
  final String imageUrl;
  final String title;
  final String publishDate;

  ArticleWidget(
      {@required this.imageUrl,
      @required this.title,
      @required this.publishDate});

  @override
  _ArticleWidgetState createState() => _ArticleWidgetState();
}

class _ArticleWidgetState extends State<ArticleWidget> {
  @override
  Widget build(BuildContext context) {
    Size _mediaQuery = MediaQuery.of(context).size;
    return Padding(
      padding: EdgeInsets.all(15),
      child: Container(
        height: _mediaQuery.height * 0.25,
        child: Column(
          children: [
            Expanded(
              child: Row(
                crossAxisAlignment: CrossAxisAlignment.start,
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: [
                  Container(
                    width: _mediaQuery.width * 0.35,
                    height: _mediaQuery.height * 0.2,
                    decoration: BoxDecoration(
                        borderRadius: BorderRadius.circular(15),
                        image: DecorationImage(
                          onError: (error ,rr){
                            print(error);
                            print(rr);
                          },
                            fit: BoxFit.cover,
                            image: widget.imageUrl == null
                                ? AssetImage("assets/notfound.png")
                                : NetworkImage(widget.imageUrl))),
                  ),
                  Expanded(
                      child: Container(
                          padding: EdgeInsets.only(right: 15, left: 15),
                          alignment: Alignment.topRight,
                          child: Column(
                            mainAxisAlignment: MainAxisAlignment.spaceBetween,
                            crossAxisAlignment: CrossAxisAlignment.start,
                            children: [
                              Container(
                                  alignment: Alignment.topRight,
                                  child: Text(
                                    widget.title,
                                    style: Theme.of(context).textTheme.headline6,
                                    textDirection: TextDirection.rtl,
                                  )),
                              Container(
                                  alignment: Alignment.bottomLeft,
                                  child: Text(
                                    widget.publishDate,
                                    textDirection: TextDirection.ltr,
                                    style: TextStyle(color: Colors.grey),
                                  ))
                            ],
                          ))),
                ],
              ),
            ),
            Divider(
              height: _mediaQuery.height * 0.05,
              thickness: 3,
            )
          ],
        ),
      ),
    );
  }
}
