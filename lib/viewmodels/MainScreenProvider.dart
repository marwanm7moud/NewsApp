import 'package:flutter/material.dart';

class MainScreenProvider with ChangeNotifier
{
  bool isDark = true;


  void changeTheme(){
    isDark =!isDark;
    notifyListeners();
  }
}