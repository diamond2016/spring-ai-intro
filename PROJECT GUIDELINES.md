# Project Guidelines

## Java Source Code Documentation

Use Javadoc to document every public and protected class, interface, enum, record, constructor, and method. Document package-private code when its purpose or behavior is not obvious from the implementation. Comments must explain the contract and the reason behind non-obvious decisions, not repeat the code.

### General Rules

- Write a complete sentence in the first line of every Javadoc comment and end it with a period.
- Describe what the type or member does, its important constraints, side effects, lifecycle, and failure behavior.
- Use `@param` for every parameter, `@return` for non-`void` results, and `@throws` for exceptions callers may need to handle.
- Use `{@code ...}` for code symbols and short code fragments. Use `{@link ...}` when linking to a related type or member improves navigation.
- Document units, nullability, mutability, ordering, thread-safety, blocking behavior, and resource ownership when applicable.
- Keep documentation current with the implementation. Update it in the same change as the code it describes.
- Do not add `@author`, `@version`, or `@since` tags unless the project explicitly requires them.
- Do not document private implementation details unless they prevent a likely maintenance mistake; prefer clear names and code first.

### Class Template

Use this template for a class, interface, enum, or record. Keep only the sections that apply.

```java
/**
 * Represents or provides [the primary responsibility of this type].
 *
 * <p>[Describe important collaboration, lifecycle, invariants, or usage constraints.]
 *
 * @see RelatedType
 */
public class Example {
	// implementation
}
```

For a class, explain its responsibility and important invariants. For an interface, describe the contract implementations must satisfy. For an enum or record, explain the meaning of its values or components and any validation rules.

### Method Template

Use this template for public and protected methods, including constructors when their behavior is not obvious.

```java
/**
 * [Verb] [what the method does and the result or state change].
 *
 * <p>[Describe side effects, ordering, blocking behavior, or important edge cases.]
 *
 * @param input [meaning, valid range, and nullability]
 * @return [meaning of the result; omit for void methods]
 * @throws IllegalArgumentException if [an argument is invalid]
 * @throws ExampleException if [the operation cannot be completed]
 */
public Result perform(Input input) throws ExampleException {
	// implementation
}
```

Use the following rules when completing the template:

- Start with a verb such as `Creates`, `Returns`, `Loads`, `Validates`, or `Starts`.
- Describe observable behavior rather than the internal algorithm.
- List every exception that is part of the method contract, including relevant unchecked exceptions. Do not list exceptions that cannot reasonably occur for callers.
- State whether a returned collection or object is mutable, whether it may be `null`, and whether it is a copy or a live view when that matters.
- For overrides, write documentation only when adding behavior or clarifying an inherited contract; otherwise use `{@inheritDoc}`.
- For simple getters, setters, constructors, and private methods, a Javadoc comment is optional when the name and signature fully express the behavior.

## Conventional Commits
format:

```text
<type>(optional-scope): short description
```

Example:

```text
feat(shell): add run start command
```

| Type | Meaning |
|---|---|
| `feat` | Add a new user-visible feature |
| `fix` | Fix a bug or incorrect behavior |
| `docs` | Documentation-only changes |
| `refactor` | Change code structure without changing behavior |
| `test` | Add or modify tests |
| `chore` | Maintenance work that does not affect application behavior |
| `build` | Changes to dependencies, Maven, Gradle, or build configuration |
| `ci` | Changes to continuous integration or deployment workflows |
| `perf` | Improve performance |
| `style` | Formatting or stylistic changes with no behavior change |
| `revert` | Revert a previous commit |

Examples for this project:

```text
feat(shell): add app info command
feat(graph): add plan node
fix(dispatcher): resume from last successful node
docs: describe Spring Shell architecture
test(shell): verify run status command
refactor(agent): extract Codex process runner
build: upgrade Spring Shell
chore: remove generated build artifacts
ci: add Maven test workflow
```

For breaking changes:

```text
feat(graph)!: replace file state format
```

Or include a footer:

```text
feat(graph): change run manifest format

BREAKING CHANGE: existing manifests must be migrated
```

Recommended rules:

- Use the imperative mood: `add`, `fix`, `update`, not `added` or `fixes`.
- Keep the subject short, ideally under  imperatively 72 characters.
- Do not end the subject with a period.
- Use `docs`, not `doc`, to follow the conventional name.
- Keep one logical change per commit.
- Use a scope when it adds clarity: `shell`, `graph`, `sonar`, `build`, or `docs`.
- Do not use `chore` for feature or bug-fix work.