import 'package:flutter/material.dart';
import 'package:newsapp/viewmodels/HomeScreenProvider.dart';
import 'package:provider/provider.dart';
class CustomButtonNavigationBar extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    var _selectedIndex = Provider.of<HomeScreenProvider>(context).selectedIndex;
    var _onItemTapped = Provider.of<HomeScreenProvider>(context).onItemTapped;


    return BottomNavigationBar(
      backgroundColor: Colors.black,
      showUnselectedLabels: true,
      unselectedItemColor: Colors.grey,
      items: const <BottomNavigationBarItem>[
        BottomNavigationBarItem(
          icon: Icon(Icons.business),
          label: 'Business',
        ),
        BottomNavigationBarItem(
          icon: Icon(Icons.sports),
          label: 'Sports',
        ),
        BottomNavigationBarItem(
          icon: Icon(Icons.school),
          label: 'Science',
        ),
        BottomNavigationBarItem(
          icon: Icon(Icons.healing),
          label: 'Health',
        ),
      ],
      currentIndex: _selectedIndex,
      selectedItemColor: Colors.amber[800],
      onTap: _onItemTapped,
    );
  }
}
