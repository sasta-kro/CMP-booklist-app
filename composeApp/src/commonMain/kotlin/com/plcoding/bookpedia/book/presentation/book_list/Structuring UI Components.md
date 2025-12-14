## Tip by Philip
The first step when making UI is to see which parts of the UI will make sense to extract to be their own separate Composable. In this case, it will be the 'search bar', 'the list item', and 'the list as a whole'

## Presentation>Domain<Data Architecture Tip
The components should be in a package, usually named "components"
- If the components are specific to a Screen, then it would be in this root package (book_list/)
- If the components are shared across many Screens of a feature, then the component package should be in the "presentation/" package.
- If the components are shared across different features, then the component can be in the "core/presentation/" package.