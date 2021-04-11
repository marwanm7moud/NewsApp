import 'package:flutter/material.dart';
import 'package:newsapp/viewmodels/MainScreenProvider.dart';
import 'view/HomeScreen/HomeScreen.dart';
import 'package:newsapp/viewmodels/HomeScreenProvider.dart';
import 'package:provider/provider.dart';

void main() {
  runApp(
      MultiProvider(
          providers: [
            ChangeNotifierProvider<MainScreenProvider>(create: (_)=>MainScreenProvider()),
            ChangeNotifierProvider<HomeScreenProvider>(create: (_)=>HomeScreenProvider()),
          ],
        child: MyApp(),

      )
      );
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    var mainProvider = Provider.of<MainScreenProvider>(context);
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'Flutter Demo',
      theme: ThemeData(
          textTheme: TextTheme(
            headline6:TextStyle(
                color: Colors.black,
                fontWeight: FontWeight.bold,
                fontSize: 16),
          ),
        primarySwatch: Colors.blue,
        scaffoldBackgroundColor: Colors.white ,
        appBarTheme: AppBarTheme(
            actionsIconTheme: IconThemeData(
                color: Colors.black
            ),
          textTheme: TextTheme(
            headline6:TextStyle(fontWeight: FontWeight.bold , color: Colors.black , fontSize: 20),
          ),
          backgroundColor: Colors.white,
          elevation: 0
        )
      ),
      darkTheme: ThemeData(

        canvasColor: Colors.grey[900],

        textTheme: TextTheme(
          headline6:TextStyle(
            color: Colors.white,
              fontWeight: FontWeight.bold,
              fontSize: 16),
        ),
          primarySwatch: Colors.blue,
          scaffoldBackgroundColor: Colors.grey[900] ,
          appBarTheme: AppBarTheme(
              textTheme: TextTheme(
                headline6:TextStyle(fontWeight: FontWeight.bold , color: Colors.white , fontSize: 20),
              ),
              backgroundColor: Colors.grey[900],
              elevation: 0,
              actionsIconTheme: IconThemeData(
                color: Colors.white
              )
          ),
          bottomNavigationBarTheme: BottomNavigationBarThemeData(
          backgroundColor: Colors.grey[900]
      )
      ),
      themeMode: mainProvider.isDark?ThemeMode.dark:ThemeMode.light,
      home:  HomeScreen(),
    );
  }
}
