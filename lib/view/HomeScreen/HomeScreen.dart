import 'package:flutter/material.dart';
import 'package:newsapp/services/NewsApi.dart';
import 'package:newsapp/viewmodels/HomeScreenProvider.dart';
import 'package:newsapp/viewmodels/MainScreenProvider.dart';
import 'package:newsapp/widgets/BottomNavigationBar.dart';
import 'package:provider/provider.dart';
class HomeScreen extends StatefulWidget {
  @override
  _HomeScreenState createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
  @override
  void initState() {
    // TODO: implement initState
    HomeScreenProvider provider =Provider.of<HomeScreenProvider>(context , listen: false);
    provider.fetchArticlesBusiness();
    provider.fetchArticlesScience();
    provider.fetchArticlesSports();
    provider.fetchArticlesHealth();
    super.initState();
  }


  @override
  Widget build(BuildContext context) {
    List<Widget> widgetOptions = Provider.of<HomeScreenProvider>(context).widgetOptions;
    var _selectedIndex = Provider.of<HomeScreenProvider>(context).selectedIndex;


    return Scaffold(
      appBar: AppBar(
        actions: [
          IconButton(
              onPressed: (){
                Provider.of<MainScreenProvider>(context , listen: false).changeTheme();
              }
              , icon: Icon(Icons.brightness_4_outlined , color: Theme.of(context).appBarTheme.actionsIconTheme.color,))
        ],
        title: Text("News App",),
      ),
      bottomNavigationBar: CustomButtonNavigationBar(),
      body: widgetOptions[_selectedIndex],
    );
  }
}
