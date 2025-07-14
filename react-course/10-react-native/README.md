# 10. React Native (Mobile)

## Overview
This module introduces React Native, a framework for building native mobile applications using React. You'll learn to create cross-platform mobile apps that work on both iOS and Android.

---

## Introduction to React Native
React Native allows you to build mobile apps using JavaScript and React, with native performance and look-and-feel.

**Key Benefits:**
- **Cross-platform:** Write once, run on iOS and Android
- **Native performance:** Uses native UI components
- **Hot reloading:** See changes instantly
- **Large ecosystem:** Many third-party libraries

**Differences from React Web:**
- Uses `View` instead of `div`
- Uses `Text` instead of `p`, `span`, etc.
- Uses `TouchableOpacity` instead of `button`
- Styling uses Flexbox (no CSS)

---

## Setting up the Environment
**Option 1: Expo (Recommended for beginners)**
```bash
npx create-expo-app MyApp
cd MyApp
npx expo start
```

**Option 2: React Native CLI**
```bash
npx react-native init MyApp
cd MyApp
npx react-native run-android  # or run-ios
```

---

## Core Components & APIs
**Basic Components:**
```jsx
import { View, Text, TouchableOpacity, ScrollView } from 'react-native';

function MyComponent() {
  return (
    <ScrollView>
      <View style={{ padding: 20 }}>
        <Text style={{ fontSize: 18, fontWeight: 'bold' }}>
          Hello, React Native!
        </Text>
        <TouchableOpacity 
          style={{ backgroundColor: 'blue', padding: 10, marginTop: 10 }}
          onPress={() => alert('Button pressed!')}
        >
          <Text style={{ color: 'white', textAlign: 'center' }}>
            Press Me
          </Text>
        </TouchableOpacity>
      </View>
    </ScrollView>
  );
}
```

**Exercise:**
- Create a simple screen with a title, some text, and a button.

---

## Navigation
Use React Navigation for screen navigation.

**Example:**
```jsx
import { NavigationContainer } from '@react-navigation/native';
import { createStackNavigator } from '@react-navigation/stack';

const Stack = createStackNavigator();

function App() {
  return (
    <NavigationContainer>
      <Stack.Navigator>
        <Stack.Screen name="Home" component={HomeScreen} />
        <Stack.Screen name="Details" component={DetailsScreen} />
      </Stack.Navigator>
    </NavigationContainer>
  );
}

function HomeScreen({ navigation }) {
  return (
    <View>
      <TouchableOpacity onPress={() => navigation.navigate('Details')}>
        <Text>Go to Details</Text>
      </TouchableOpacity>
    </View>
  );
}
```

---

## Styling in React Native
React Native uses a subset of CSS with Flexbox for layout.

**Example:**
```jsx
import { StyleSheet } from 'react-native';

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#f5f5f5',
  },
  title: {
    fontSize: 24,
    fontWeight: 'bold',
    color: '#333',
    marginBottom: 20,
  },
  button: {
    backgroundColor: '#007AFF',
    paddingHorizontal: 20,
    paddingVertical: 10,
    borderRadius: 8,
  },
  buttonText: {
    color: 'white',
    fontSize: 16,
    fontWeight: '600',
  },
});
```

---

## Mini-Project: Mobile Todo App
- Create a simple todo app with React Native
- Add navigation between screens
- Use local storage to persist todos

---

## Best Practices
- Use Expo for faster development and easier setup
- Follow platform-specific design guidelines
- Test on both iOS and Android devices
- Use proper error handling for mobile-specific APIs

---

## Glossary
- **React Native:** Framework for building native mobile apps with React
- **Expo:** Platform and tools for React Native development
- **Native Components:** UI components that map to native iOS/Android components
- **Flexbox:** Layout system used in React Native
- **React Navigation:** Popular navigation library for React Native

---

**Next:** [Real-World Projects](../11-projects/README.md)