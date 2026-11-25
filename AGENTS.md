# Project Instructions

## Ask before making changes

- Always ask James Brannan before making any change, and wait for explicit approval before proceeding.
- An explicit instruction to create, edit, delete, or fix specified files or content is approval for that scope; do not ask for the same approval again. If the requested change or its scope is unclear, describe the proposed changes and ask before proceeding.
- Describe the proposed changes and affected files before asking for approval.
- Do not treat a request to discuss, review, investigate, or propose a design as permission to modify files.
- Read-only inspection may proceed without approval. Ask before commands that modify files, including builds or tests that generate output.
- Keep approved changes within the scope described. Ask again before making additional changes outside that scope.

## Python and Jupyter notebooks

When working with Python in a Jupyter notebook, paste the code and output in the chat instead of adding them to or modifying the notebook, unless the user explicitly requests notebook edits.

## Attribution requirements

These instructions apply only to Python code in this repository, including Python code in Jupyter notebooks and Python examples provided for this project.

- When adapting existing code, retain existing copyright and license notices and identify the original source or author when known.
- Include a license reference only when it accurately reflects the applicable license.

Example header for an adaptation of the MIT-licensed routing example:

```python
# Adapted from the routing example credited to Marco Fago.
# MIT License; see the repository LICENSE.
```

## Headers for new Java files only

The header requirements in this section apply only to newly created `.java` files. They do not apply to Markdown, Python, notebooks, JSON, XML, SQL, configuration files, images, videos, or other non-Java files. Do not add headers to non-Java files or request header-related approval for those files.

Every newly created Java file must include a complete, readable Javadoc type header stating:

- File: the actual filename, including its extension (for example, PdfDocumentService.java).
- Author: James Brannan.
- Date: the creation date in YYYY-MM-DD format.
- Description: a brief explanation of the file's purpose.
- Copyright: the applicable year and James Brannan as copyright holder.
- License statement: explicitly state that the file is licensed under the MIT License, refer to the project-root LICENSE file for the full terms, and include SPDX-License-Identifier: MIT.

Do not use an author-only comment or an abbreviated attribution in place of this header. Populate the filename, date, year, and description with actual values; do not leave template placeholders in created files.

Use a Javadoc type header (`/** ... */`) immediately before the class, interface, enum, or record declaration and its annotations, after the package and import statements. Describe the type in the opening sentence and use the standard Javadoc tags shown below. Do not use a plain block comment as a substitute.

Use the current version from the Java file's owning Maven project's `pom.xml` for @version. Use the version in which the type was introduced for @since; if that cannot be established, ask rather than inventing it. The template version below is illustrative; verify the actual version before use.

Use standard tags @author, @version, and @since. Use custom tags @file, @date, @copyright, and @license for the remaining metadata, each on its own line. These four tags are project conventions, not built-in Javadoc tags; if generating Javadoc, register them using the Javadoc tool's custom-tag configuration. Keep the brief description as the opening prose, matching a conventional Java class header; do not invent an @description tag.

Java header template:

```java
/**
 * Briefly describes the purpose and responsibility of this class.
 *
 * @file ClassName.java
 * @author James Brannan
 * @date YYYY-MM-DD
 * @version 0.0.1-SNAPSHOT
 * @since 0.0.1-SNAPSHOT
 * @copyright Copyright (c) YYYY James Brannan.
 * @license MIT License. See LICENSE in the project root for the full terms.
 *          SPDX-License-Identifier: MIT
 */
```

Do not retrofit headers into existing Java files without approval. Preserve existing third-party copyright and license notices in all files; do not replace them with the project header.
