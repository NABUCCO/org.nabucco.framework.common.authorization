NABUCCO Framework (README)
==========================

NABUCCO Framework - Component Based and Model-Driven Development
The NABUCCO Framework is the modern approach for developing software in a model-driven way using a component model.

Requirements
------------
The Frameworks available in the Open Source environment for MDA and component based development are difficult to combine for an integrated solution. The NABUCCO Framework closes this gap and provides an extensive construction kit of subject-specific and technical solutions. The concepts required in the Enterprise environment (for example, multi-client capability, client-specific customizability, scalability and much more) are already an integral component of the Framework.

Overview
--------
* Tool neutral MDA approach with textual DSL
* DSL for components, services, data-types etc.
* User Interface for RCP, Web and Portal
* Eclipse Generator Plugin
* Supported by UML2 and other meta-models
* Direct creation of Java, XML
* Integrated up-to-date documentation
* Short turnaround times with the development
* Extendable through customized DSL
* NABUCCO is 100% Open Source

Solutions
---------
The NABUCCO Framework consists of an MDA approach with a separate DSL, as well as components based thereon, for the development of products and customized applications.

Objectives
--------------------------------
* Creation of reusable components
* Complete multi-client capability of all components
* Depiction of recurring functions (Best Practices)
* Independence from tools and special technologies
* Supported by UML as well as textual DSLs
* Fast development, paired with qualitatively high value results



*Find more Information on our [website](http://nabuccosource.org/).*

*The complete documentation may be found on our [Confluence](http://www.nabucco-source.org/confluence/). Sign up for free to add comments and help us to improve NABUCCO.*

*Found any bugs? Want to track improvements? Report them in our public [Jira](http://www.nabucco-source.org/jira/).*


org.nabucco.framework.common.authorization
==========================================
The authorization component enables the complete management of users, groups and privileges. In doing so, the components can perform the management themselves or they can be connected through an adapter to an LDAP or Active Directory.

The main functions are

* Management of users and their assignment to groups
* Allocation of privileges to groups or to users. Privileges for groups are assigned through the users
* Management of roles and their privileges. Roles can be assigned to groups and users
* Authentication (if necessary, externally through LDAP) of users
* Reviewing each application request for authorization
* Dissolving the rights of a user
* Dissolving the groups of a user