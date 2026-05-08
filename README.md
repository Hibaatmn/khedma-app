# khedma-app

Application Android **Khedma** (plateforme de services) avec structure Gradle complète, Firebase Auth + Firestore, dashboards Client/Provider et navigation par onglets.

## Prérequis
- Android Studio (JDK 17)
- SDK Android (compileSdk/targetSdk 34)
- Un projet Firebase avec Authentication (Email/Password) + Firestore

## Lancer le projet
1. Ouvrir le dossier du projet dans Android Studio.
2. Remplacer `app/src/main/google-services.json` par votre fichier Firebase réel.
3. Synchroniser Gradle (`Sync Project with Gradle Files`).
4. Lancer l'app sur un émulateur ou appareil.

## Build en CLI
```bash
./gradlew :app:assembleDebug
```

## Firestore Rules
Des règles de base sont fournies dans `firestore.rules`.
